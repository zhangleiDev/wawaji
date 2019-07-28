#!/bin/bash
 docker run -dit \
	    -p 9999:80 \
	    --name=nginx \
	    -v /Users/zhanglei/Desktop/zhuawawa/docker/nginx/conf:/etc/nginx/conf.d \
	    -v /Users/zhanglei/Desktop/zhuawawa/docker/nginx/html:/usr/share/nginx/html \
            nginx:v1
