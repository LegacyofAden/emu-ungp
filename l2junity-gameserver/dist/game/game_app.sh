#!/bin/bash

# Application constants.
SERVICE_NAME="l2junity_game"
APP_JAR="l2junity-game.jar"
LOCK_FILE=/tmp/${SERVICE_NAME}
OUTPUT_LOG=./${SERVICE_NAME}.log

# Color constants.
SETCOLOR_GREEN="\\033[1;32m"
SETCOLOR_RED="\\033[1;31m"
SETCOLOR_DEFAULT="\\033[0;39m"

# A general function to start the application.
# & is mandatory for this function, otherwise application won't go into the background.
function startApp() {
    # Linux basics.
    if [[ $USER == "root" ]]; then
	echo "Running the application with root user may cause lethal security issues. Please dedicate a user for your application."
	exit 1
    fi

    # Check whether lock file is present.
    if [ -f $LOCK_FILE ]; then
	echo "Lock file is present, because service '${SERVICE_NAME}' is already running."
	exit 1
    fi

    # Create lock file.
    touch $LOCK_FILE

    # Inform the user.
    echo "Service '${SERVICE_NAME}' is started in background."

    # Start the application finally.
    java -Xms1024m -Xmx1536m -jar $APP_JAR > $OUTPUT_LOG 2>&1

    # If exit code is restart, then restart the application, otherwise bye-bye!
    if [ $? -eq 2 ]; then
	stopApp
	sleep 1
	startApp &
    else
	stopApp
    fi
}


# A general function to stop the application. Force kill is prophibited here!
function stopApp() {
    # Remove the lock file.
    rm -f $LOCK_FILE

    # Get the application PID.
    local appPID=$(ps x | grep $APP_JAR | grep -v grep | awk '{print $1}')

    # If PID exist, then kill the application.
    if [[ ! -z $appPID ]]; then
	kill $appPID
    fi

    # Inform the user.
    echo "Service '${SERVICE_NAME}' is stopped."
}

# Check the status running of the application.
function statusApp() {
    # Get the application PID.
    local appPID=$(ps x | grep $APP_JAR | grep -v grep | awk '{print $1}')

    # Inform user wheter service is running or not.
    echo -ne "[ "
    if [[ ! -z $appPID ]]; then
	echo -ne $SETCOLOR_GREEN
	echo -ne "ONLINE"
	echo -ne $SETCOLOR_DEFAULT
	echo " ] Service '${SERVICE_NAME}' is running."
    else
	echo -ne $SETCOLOR_RED
	echo -ne "OFFLINE"
	echo -ne $SETCOLOR_DEFAULT
	echo " ] Service '${SERVICE_NAME}' is NOT running."
	# In case life happens. Unexpected outage, etc...
	[ -f $LOCK_FILE ] && echo "However the lock file '${LOCK_FILE}' is present, possibly an unexpected shutdown event happened recently. Please use '${0} stop' to cleanup."
    fi
}

# A simple function to show the usage of this service.
function helpApp() {
    echo "Usage: $0 (start|stop|status)"
}

# User choice below.
case $1 in
    'start')
	startApp &
    ;;

    'stop')
	stopApp
    ;;

    'status')
	statusApp
    ;;

    *)
	helpApp
    ;;
esac
