CC = g++
LibName = library
ExName = main

run: rebuild execute
execute:
		sudo ./${ExName}
rebuild: clean build
build: ex lib
ex: ex.o
		${CC} -o ${ExName} ${ExName}.o -pthread -ldl
ex.o:
		${CC} -c -fPIC ${ExName}.cpp
lib: lib.o
		${CC} -shared -o ${LibName}.so ${LibName}.o -lrt 
lib.o:
		${CC} -c -fPIC ${LibName}.cpp 
clean:
		rm -f ${ExName} *.o *.so
