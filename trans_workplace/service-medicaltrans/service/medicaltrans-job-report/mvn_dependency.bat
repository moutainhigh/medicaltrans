	call mvn dependency:copy-dependencies
	xcopy .\target\*.jar .\target\dependency /y