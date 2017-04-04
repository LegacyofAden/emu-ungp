package org.l2junity.commons.database;

import lombok.extern.slf4j.Slf4j;
import org.l2junity.commons.concurrent.ExecDelayedQueue;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author PointerRage
 *
 */
@Slf4j
public class DelayedSqlRunner extends ExecDelayedQueue<DelayedSql> {
    private final AtomicBoolean avaliable = new AtomicBoolean(true);
    private final IConnectionFactory connectionFactory;
    private Connection con;
    public DelayedSqlRunner(ScheduledExecutorService executor, IConnectionFactory connectionFactory, long delay, TimeUnit timeUnit) {
        super(executor, delay, timeUnit);
        this.connectionFactory = connectionFactory;
    }

    @Override
    protected void processQueue() {
        try (Connection con = connectionFactory.getConnection()) {
            con.setAutoCommit(false);
            this.con = con;
            try {
                super.processQueue();
            } finally {
                this.con = null;
                con.commit();
                con.setAutoCommit(true);
            }
            avaliable.set(true);
        } catch (SQLException e) {
            if(avaliable.getAndSet(false))
                log.warn("", e);
        }
    }

    @Override
    protected void runDelayed(DelayedSql delayed) {
        Savepoint savepoint = null;
        try {
            savepoint = con.setSavepoint();
        } catch (SQLException e) {
            // savepoint not supported yet.
        }

        try {
            delayed.runQuery(con);
        } catch (SQLException e) {
            rollback(con, savepoint);
        } catch (Throwable e) {
            rollback(con, savepoint);
            throw e;
        }

        if (savepoint != null) {
            try {
                con.releaseSavepoint(savepoint);
            } catch (SQLException e) {
                // handling?
            }
        }
    }

    private static /*inline*/ void rollback(Connection con, Savepoint savepoint) {
        if(savepoint != null) {
            try {
                con.rollback(savepoint);
            } catch(SQLException e1) {
                //handling?
            }
        }
    }

    @Override
    public boolean add(DelayedSql delayed) throws RuntimeException {
        if(!avaliable.get())
            throw new RuntimeException("Database in down");
        return super.add(delayed);
    }

    @Override
    public void block() {
        super.block();
        processQueue();
    }
}
