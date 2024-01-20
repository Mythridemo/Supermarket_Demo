import software.amazon.awscdk.core.*;
import software.amazon.awscdk.services.ecs.*;

public class SupermarketCheckoutStack extends Stack {

    public SupermarketCheckoutStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public SupermarketCheckoutStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        // Define ECS Cluster
        Cluster cluster = Cluster.Builder.create(this, "SupermarketCheckoutCluster")
                .clusterName("supermarket-checkout-cluster")
                .build();

        // Define ECS Task Definition
        TaskDefinition taskDefinition = TaskDefinition.Builder.create(this, "SupermarketCheckoutTaskDef")
                .compatibility(Compatibility.FARGATE)
                .cpu("256")
                .memoryMiB("512")
                .build();

        // Add container definition to the task definition
        taskDefinition.addContainer("SupermarketCheckoutContainer", ContainerDefinitionOptions.builder()
                .image(ContainerImage.fromRegistry("your-docker-image"))
                .memoryLimitMiB(256)
                .cpu(256)
                .build());

        // Define ECS Service
        FargateService service = FargateService.Builder.create(this, "SupermarketCheckoutService")
                .cluster(cluster)
                .taskDefinition(taskDefinition)
                .assignPublicIp(true)  // Assign a public IP if needed
                .serviceName("supermarket-checkout-service")
                .desiredCount(1)  // Number of tasks to run
                .build();
    }

    public static void main(final String[] args) {
        App app = new App();
        new SupermarketCheckoutStack(app, "SupermarketCheckoutStack");
        app.synth();
    }
}
