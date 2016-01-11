#!/usr/bin/env bash
#PREFIX="http://bits.beta.wmflabs.org/en.wikipedia.beta.wmflabs.org"
PREFIX="http://127.0.0.1:8080/w"
BASE_PATH="`dirname $0`/.."

wget "$PREFIX/load.php?debug=true&lang=en&modules=skins.minerva.base.reset|skins.minerva.content.styles|ext.cite.style|mobile.app.pagestyles.android&only=styles&skin=vector&version=&*" -O "$BASE_PATH/app/src/main/assets/styles.css"
wget "$PREFIX/load.php?debug=true&lang=en&modules=skins.minerva.base.reset|skins.minerva.content.styles|ext.cite.style|mobile.app.preview&only=styles&skin=vector&version=&*" -O "$BASE_PATH/app/src/main/assets/preview.css"
wget "$PREFIX/load.php?debug=true&lang=en&modules=mobile.app.pagestyles.android.night&only=styles&skin=vector&version=&*" -O "$BASE_PATH/app/src/main/assets/night.css"
