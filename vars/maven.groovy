

def call(String args) {
    sh "git checkout master"
    sh "mvn -U clean test${args ?: ''}"
}

return this
