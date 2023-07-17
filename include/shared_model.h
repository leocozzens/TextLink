#ifndef MODEL_STRUCTS_H
#define MODEL_STRUCTS_H

// POSIX headers
#include <pthread.h>

#define BUFF_SIZE 256
#define USERDATA_SIZE sizeof(UserData)

typedef struct {
    char msg[BUFF_SIZE];
} UserData;

#endif