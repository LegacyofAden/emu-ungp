#!/bin/sh

# chkconfig: 2345 80 80
# description: The loginserver.

### BEGIN INIT INFO
# Provides:          login_service.sh
# Required-Start:    hostname $local_fs $syslog $network mysql
# Required-Stop:     hostname $local_fs $syslog $network mysql
# Default-Start:     2 3 4 5
# Default-Stop:      0 1 6
### END INIT INFO

# User must change these parameters to his own!
run_as=your_user_name
app_home="/home/${run_as}/l2junity-gameserver/login"
app_script=login_app.sh

# A function to manage your app.
app() {
        if [ "x$USER" != "x$run_as" ]; then
                su - "$run_as" -c "cd $app_home && ./${app_script} $1"
        else
                "cd ${app_home} && ./${app_script} $1"
        fi
}

# User choice below.
case "$1" in
        start)
                app start
                ;;
        stop)
                app stop
                ;;
        status)
                app status
                ;;
        *)
                echo "Usage: $0 {start|stop|status}"
esac

exit 0

