pushd $PROJECT_BUILD_DIR

# Stanford Parser
wget --trust-server-name http://nlp.stanford.edu/software/stanford-parser-2012-11-12.zip
unzip stanford-parser-2012-11-12.zip

# JPype
wget --trust-server-name http://sourceforge.net/projects/jpype/files/latest/download?source=files
pip install JPype*.zip

# Stanford Parser interface for Python
wget --trust-server-name https://projects.csail.mit.edu/spatial/images/f/f8/Stanford-parser-python-r22186.tar.gz
tar -xf Stanford-parser-python-r22186.tar.gz

popd
