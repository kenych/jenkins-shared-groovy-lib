

def call() {
    sh "mvn -U clean test cobertura:cobertura -Dcobertura.report.format=xml"
}

return this
