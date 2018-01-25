import org.junit.Before
import org.junit.Test
import util.ScriptLoader

import static org.hamcrest.CoreMatchers.equalTo
import static org.junit.Assert.assertThat

class mavenTest {

    def maven
    def shellCommands = []

    @Before
    void setUp() {
        shellCommands = []
        maven = ScriptLoader.load("vars/maven.groovy")
        maven.metaClass.sh = { String s -> shellCommands.add(s) }
    }

    @Test
    void shouldCallMavenWithArgs() {
        maven.call(" cobertura:cobertura -Dcobertura.report.format=xml")

        assertThat(shellCommands.size(), equalTo(2))
        assertThat(shellCommands.toString(), equalTo("[git checkout master, mvn -U clean test cobertura:cobertura -Dcobertura.report.format=xml]"))
    }

    @Test
    void shouldCallMavenWithNoArgs() {
        maven.call()

        assertThat(shellCommands.size(), equalTo(2))
        assertThat(shellCommands.toString(), equalTo("[git checkout master, mvn -U clean test]"))
    }
}
