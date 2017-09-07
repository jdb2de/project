#! /bin/bash

MAVEN="/usr/bin/mvn"
IDENTITY="sonar-cloud-identity"

project=$1

# Check if there is a identity file
if [ -f "$IDENTITY" ]; then
  source ${IDENTITY}
else
  organization=$2
  login=$3
fi

help_message() {
  echo "Try: sh sonar-cloud.sh [PROJECT (core|gui|sample)] [ORGANIZATION_ID] [LOGIN_TOKEN]"
}

exit_message() {
  if [ "" != "$1"  ]; then
    echo "$1"
  fi

  help_message

  exit -1
}

# Check the maven installation
if [ ! -f "$MAVEN" ]; then
  echo "Attention: mvn (maven) is not installed"
  exit -1
fi

# Check the project name
if [ "" == "$project" ]; then
  exit_message "Missing: project name"
fi

# Check if project name is valid
if ! [[ "$project" =~ ^(core|gui|sample)$ ]]; then
  exit_message  "[$project] is not a valid project name, it must be \"core\" or \"gui\" or \"sample\" "
fi

# Check if project directory exists
project_dir="jdb2de-$project"
if [ ! -d "$project_dir" ]; then
  echo "Project directory [$project_dir] not found, please check you path"
  exit -1
fi

# Check organization identification
if [ "" == "$organization" ]; then
  exit_message "Missing: organization id"
fi

# Check login token
if [ "" == "$login" ]; then
  exit_message "Missing: login token"
fi

echo "************************"
echo "Sonar Validation Params"
echo "************************"
echo "project name : $project"
echo "organization : $organization"
echo "login token  : $login"
echo "************************"


# Change to project directory
cd ${project_dir}

# Start maven validation
mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent package sonar:sonar \
    -Dsonar.host.url=https://sonarcloud.io \
    -Dsonar.organization="$organization" \
    -Dsonar.login="$login"

# Back to previous directory
cd -
echo " "