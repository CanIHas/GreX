package can.i.has.grex.experiments.config

import can.i.has.grex.experiments.Result
import can.i.has.grex.experiments.runner.ExperimentRunner

abstract class AbstractConfigProvider<R extends Result> implements ConfigProvider<R>{

    static interface ProviderRun<R2 extends Result> {
        void begin()
        void end()
        boolean hasNext()
        Config getNext(ExperimentRunner<R2> runner)
    }

    protected ProviderRun<R> currentRun = null

    abstract protected ProviderRun<R> getNewRun()

    @Override
    void begin() {
        assert currentRun == null
        currentRun = newRun
        currentRun.begin()
    }

    @Override
    boolean hasNext() {
        assert currentRun
        currentRun.hasNext()
    }

    @Override
    Config getNext(ExperimentRunner<R> runner) {
        assert currentRun
        def out = currentRun.getNext(runner)
        out.symbolicConfig.keySet().each { String key ->
            out.symbolicConfig[key] = "${out.symbolicConfig[key]}"
        }
        out
    }

    @Override
    void end() {
        assert currentRun
        currentRun.end()
    }

    @Override
    void reset() {
        assert ! currentRun?.hasNext()
        currentRun = null
    }
}
