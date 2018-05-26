#include <dlfcn.h>
#include <pthread.h>
#include <iostream>
#include <unistd.h> 

using namespace std;

const char* readAsyncName = "threadToRead";
const char* writeAsyncName = "threadToWrite";

void (*readAsync)(void* arguments);
void (*writeAsync)(void* arguments);

int main() {
    pthread_t thread;
    pthread_attr_t attr;
    pthread_attr_init(&attr);
    
    void* libDescriptor = dlopen("./library.so", RTLD_LAZY);
    if(!libDescriptor){
        cout << dlerror() << endl;
        return 1;     
    }
    readAsync = (void(*)(void*))dlsym(libDescriptor, readAsyncName);
    writeAsync = (void(*)(void*))dlsym(libDescriptor, writeAsyncName);
    if(readAsync == NULL || writeAsync == NULL){
        printf("Error\n");
        dlerror();
        return 1;
    }
    cout << pthread_create(&thread, &attr, (void*(*)(void*))writeAsync, NULL) << endl;
    sleep(0.5);
    readAsync(NULL);



    pthread_join(thread, NULL);
    dlclose(libDescriptor);
    return 0;
}


