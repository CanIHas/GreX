package can.i.has.experiments


interface ConfigYield {
    /**
     *
     * @param closure (NamedList config)
     */
    void eachConfig(Closure<Void> closure)
}
