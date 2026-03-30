#!/usr/bin/env bash
set -euo pipefail

# Build and deploy script (POSIX) converted from build.bat
# Usage:
#   ./build.sh
# Optional: set TOMCAT_WEBAPPS to point to your Tomcat webapps directory.

PROJECT_DIR="$(cd "$(dirname "$0")" && pwd -P)"
APP_NAME=newspaper
SRC_DIR="$PROJECT_DIR/src/main/java"
WEB_DIR="$PROJECT_DIR/src/main/webapp"
BUILD_DIR="$PROJECT_DIR/build"
LIB_DIR="$PROJECT_DIR/lib"
TOMCAT_WEBAPPS="${TOMCAT_WEBAPPS:-/usr/local/tomcat/webapps}"
APP_PATH="$TOMCAT_WEBAPPS/$APP_NAME.war"

echo "Projet = $PROJECT_DIR"

# ====== RESET BUILD ======
rm -rf "$BUILD_DIR"
mkdir -p "$BUILD_DIR/WEB-INF/classes"
mkdir -p "$BUILD_DIR/WEB-INF/lib"

# ====== COPIE DES LIB ======
if [ -d "$LIB_DIR" ]; then
  echo "Copie des libs..."
  find "$LIB_DIR" -maxdepth 1 -type f -name '*.jar' -exec cp -f {} "$BUILD_DIR/WEB-INF/lib/" \;
fi

# ====== COMPILATION ======
echo "Compilation des sources Java..."
find "$SRC_DIR" -name '*.java' > "$PROJECT_DIR/sources.txt"

# Build classpath: target classes dir plus all jars from lib
CP="$BUILD_DIR/WEB-INF/classes"
if [ -d "$LIB_DIR" ]; then
  for jar in "$LIB_DIR"/*.jar; do
    [ -e "$jar" ] || continue
    CP="$CP:$jar"
  done
fi

javac -cp "$CP" -d "$BUILD_DIR/WEB-INF/classes" -parameters @"$PROJECT_DIR/sources.txt"

rm -f "$PROJECT_DIR/sources.txt"

# ====== COPIE WEB ======
echo "Copie vers le build..."
if [ -d "$WEB_DIR" ]; then
  cp -r "$WEB_DIR"/* "$BUILD_DIR/" || true
fi

# ====== CREATION WAR ======
echo "Creation du war..."
(
  cd "$BUILD_DIR"
  jar -cvf "$APP_NAME.war" * > /dev/null
)

# ====== DEPLOIEMENT ======
echo "Copie vers le serveur ..."
mkdir -p "$(dirname "$APP_PATH")"
if [ -f "$APP_PATH" ]; then
  rm -f "$APP_PATH"
fi
cp -f "$BUILD_DIR/$APP_NAME.war" "$APP_PATH"

echo "Deploiement termine avec succes."
