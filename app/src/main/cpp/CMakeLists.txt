cmake_minimum_required(VERSION 3.22)
project(wifihack)

add_library(wps SHARED wpsbruter.c)
add_library(hccl SHARED hccl.cpp)

find_library(log log)
target_link_libraries(wps ${log})
target_link_libraries(hccl ${log})
