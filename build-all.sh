#!/bin/bash

function build_dir()  # $1 is the dir to get it
{
    cd $1
    ./build.sh
    cd ..
}

echo " Building "

build_dir "appariement"

build_dir "hebergeur"

build_dir "joueur"

build_dir "proba"

echo " Done "