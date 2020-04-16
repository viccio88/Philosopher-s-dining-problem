package dining_problem.strategies;

public class StrategyFactory {
    public ActingStrategy create(String className){
        switch (className){
            case "HierarchicalStrategy": return new HierarchicalStrategy();
            default: return new TakeRightFirstStrategy();
        }
    }
}
