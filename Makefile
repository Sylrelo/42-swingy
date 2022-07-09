run:
	@mvn package -Dmaven.test.skip
	@java -jar target/swingy-1.0.jar