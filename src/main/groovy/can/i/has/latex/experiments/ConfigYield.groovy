package can.i.has.latex.experiments


interface ConfigYield {
    /**
     *
     * @param closure (NamedList config)
     */
    void eachConfig(Closure<Void> closure)
}
