/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package example.plugin;

import org.gradle.api.Project;
import org.gradle.api.Plugin;

public class GreetingPlugin implements Plugin<Project> {
    public void apply(Project project) {
        project.getTasks().register("greet", GreetTask.class);
    }
}