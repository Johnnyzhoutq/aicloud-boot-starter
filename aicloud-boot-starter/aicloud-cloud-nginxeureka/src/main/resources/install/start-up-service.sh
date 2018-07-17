APP_NAME='aicloud-cloud-nginxeureka-1.0-SNAPSHOT-exec'
command='nohup java -DSYSCODE='aicloud-cloud-nginxeureka-1.0-SNAPSHOT-exec' -jar aicloud-cloud-nginxeureka-1.0-SNAPSHOT-exec.jar'
log_file_url="log.log"
pid_file="web.pid"

start(){
    if [ "$log_file_url" != "" ]; then
		echo "STARTING $APP_NAME ....."
        exec $command  > "$log_file_url" 2>&1 &
		echo $! > "$pid_file"
    else
		echo "STARTING $APP_NAME ....."
        exec $command &
		echo $! > "$pid_file" &
    fi
}
stop(){
	echo "STOPING $APP_NAME ....."
	PID=$(cat "$pid_file")
	kill -9 $PID
}

case "$1" in
	start)
		start
		;;
	stop)
		stop
		;;
	restart)
		stop
		start
		;;
	*)
		printf 'Usage: %s {start|stop|restart}\n'
		exit 1
		;;
esac
