package dining_problem.strategies;

public class StrategyFactory {
    public ActingStrategy create(String className){
        switch (className){
            case "HierarchicalStrategy": return new HierarchicalStrategy();
            case "SemaphoreStrategy": return new SemaphoreStrategy();
            case "GuardedBlocks": return new GuardedBlocks();
            default: return new TakeRightFirstStrategy();
        }
    }
}
