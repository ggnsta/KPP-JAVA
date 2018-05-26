//
// Created by sergey on 4/13/18.
//

#include <cstdio>
#include <iostream>
#include <signal.h>
#include <aio.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/wait.h>
#include <stdio.h>
#include <string.h>

using namespace std;

#define SIGREAD SIGUSR1
#define SIGWRITE SIGUSR2

void initStructure(aiocb *str);

const char *filesNames[]= {
    "./files/file1.txt",
    "./files/file2.txt",
    "./files/file3.txt",
    "./files/file4.txt"
};

int currentFileLength = 0;
int currentFileIndex = 0;
int iteration = 0;
const char resultName[] =  "./files/Result.txt";
const int _number = 100000;

char buffer0[_number];
char buffer1[_number];
char buffer2[_number];
char buffer3[_number];

int difference = 0;

aiocb readIOStruct0;
aiocb readIOStruct1;
aiocb readIOStruct2;
aiocb readIOStruct3;

aiocb writeIOStruct0;

pthread_mutex_t mutex;

void readAsync(int d, siginfo_t* info, void* arg);
void writeAsync(int d, siginfo_t* info, void* arg);
int getFileSize(FILE* fileDes);
void nullBuff();
void unlockMutex(int d, siginfo_t* info, void* arg);

extern "C" void threadToRead(void* arguments){
    initStructure(&readIOStruct0);
    initStructure(&readIOStruct1);
    initStructure(&readIOStruct2);
    initStructure(&readIOStruct3);
    //initStructure(&writeIOStruct0);
    // initStructure(&writeIOStruct1);
    // initStructure(&writeIOStruct2);
    // initStructure(&writeIOStruct3);
    readIOStruct0.aio_fildes = fileno(fopen(filesNames[0], "r"));
    readIOStruct1.aio_fildes = fileno(fopen(filesNames[1], "r"));
    readIOStruct2.aio_fildes = fileno(fopen(filesNames[2], "r"));
    readIOStruct3.aio_fildes = fileno(fopen(filesNames[3], "r"));
    readIOStruct0.aio_buf = buffer0;
    readIOStruct1.aio_buf = buffer1;
    readIOStruct2.aio_buf = buffer2;
    readIOStruct3.aio_buf = buffer3;
    readIOStruct0.aio_sigevent.sigev_value.sival_ptr = &readIOStruct0;
    readIOStruct1.aio_sigevent.sigev_value.sival_ptr = &readIOStruct1;
    readIOStruct2.aio_sigevent.sigev_value.sival_ptr = &readIOStruct2;
    readIOStruct3.aio_sigevent.sigev_value.sival_ptr = &readIOStruct3;


    struct sigaction sa;
    sa.sa_flags =SA_RESTART | SA_SIGINFO;
    sigemptyset(&sa.sa_mask);
    sa.sa_sigaction = unlockMutex;//readAsync;
    if(sigaction(SIGREAD, &sa, NULL) == -1){
        cout<<"error parent thread\n"<<endl;
        return ;
    }
    readAsync(NULL, NULL, NULL);
    // while(1){
    //     int m;
    //     wait(&m);
    // }
}

// second 
extern "C" void threadToWrite(void* arguments){
    initStructure(&writeIOStruct0);    
    writeIOStruct0.aio_sigevent.sigev_signo = SIGREAD;
    writeIOStruct0.aio_fildes = fileno(fopen(resultName, "w+"));
    struct sigaction sa;
    sa.sa_flags = SA_RESTART | SA_SIGINFO;
    sigemptyset(&sa.sa_mask);
    sa.sa_sigaction = writeAsync;
    pthread_mutex_init(&mutex, NULL);    
    //writeIOStruct.aio_fildes = fileno(fopen(resultName, "w+"));
    if(sigaction(SIGWRITE, &sa, NULL) ==-1){
        cout<<"error child thread"<<endl;
        return ;
    }

    // struct sigaction sa1;
    // sa1.sa_flags =SA_RESTART | SA_SIGINFO;
    // sigemptyset(&sa.sa_mask);
    // sa1.sa_sigaction = unlockMutex;//readAsync;
    // if(sigaction(SIGREAD, &sa, NULL) == -1){
    //     cout<<"error parent thread\n"<<endl;
    //     return ;
    // }
    while(iteration != 4){
        int m;        
        wait(&m);      
    }
    printf("end");
    pthread_mutex_destroy(&mutex);
}

void readAsync(int d, siginfo_t* info, void* arg){
   printf("\nmain thread\n");
    // if(currentFileLength <= 0 && iteration != 0){
    //     currentFileIndex++;
    // }
    // if(currentFileLength <= 0){
    //     if(currentFileIndex > 3){
    //         exit(0);
    //     }
    //     auto f = fopen(filesNames[currentFileIndex], "r");
    //     currentFileLength = getFileSize(f);
    //     readIOStruct.aio_fildes = fileno(fopen(filesNames[currentFileIndex], "r"));
    //     printf("%d",currentFileLength);
    //     readIOStruct.aio_offset = 0;
    // }
    // if(iteration != 0){
    //     readIOStruct.aio_offset += difference;
    //     writeIOStruct.aio_offset += difference;
    //     currentFileLength -= difference;
    // }
    //iteration++;
    //nullBuff();
    aio_read(&readIOStruct0);
    aio_read(&readIOStruct1);
    aio_read(&readIOStruct2);
    aio_read(&readIOStruct3);
}

void writeAsync(int d, siginfo_t* info, void* arg){
    pthread_mutex_lock(&mutex);
    aiocb* str= (aiocb*)info->si_value.sival_ptr;
    printf("child thread\n"); 
    printf("%s", (char*)str->aio_buf);
    difference = strlen((char*)str->aio_buf);

    auto f = fopen(resultName, "r");
    writeIOStruct0.aio_fildes = fileno(fopen(resultName, "w+"));    
    currentFileLength = getFileSize(f);
    //if(iteration != 0){
        writeIOStruct0.aio_offset = currentFileLength;
    //}
    writeIOStruct0.aio_nbytes = difference;
    printf("\nbyte read %d\n", difference);
    printf("\nbyte write %d\n", currentFileLength);
    //printf("%d, %", difference);
    //printf("\n%d\n", difference);
    //writeIOStruct.aio_nbytes = difference;
    aio_write(&writeIOStruct0);
}

void unlockMutex(int d, siginfo_t* info, void* arg){
    iteration++;
    //printf("%d", iteration);    
    pthread_mutex_unlock(&mutex);
    // if(iteration == 3){
    //     pthread_mutex_destroy(&mutex);
    //     exit(0);
    // }
}

void initStructure(aiocb* str){
    //str->aio_buf = buffer;
    str->aio_offset = 0;
    str->aio_nbytes = _number;
    str->aio_reqprio = 0;
    str->aio_sigevent.sigev_notify = SIGEV_SIGNAL;
    str->aio_sigevent.sigev_value.sival_ptr = NULL; 
    str->aio_sigevent.sigev_signo = SIGWRITE;     
}

int getFileSize(FILE* fileDes){
    fseek(fileDes, 0, SEEK_END);
    return ftell(fileDes);
}

// void nullBuff(){
//     for(int i = 0; i < _number; i++){
//         buffer[i] = 0;
//     }
// }