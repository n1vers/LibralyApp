package ee.ivkhkdev.factory;


public class Factory {
    private static Factory FACTORY = null;
    private static Configuration configuration;
    private Factory() {

    }
    public static Factory getInstance(Configuration configuration) {
        FACTORY.configuration = configuration;
        if(FACTORY == null) {
            Factory.FACTORY = new Factory();
        }
        return Factory.FACTORY;
    }
    public <T> T getobject(String name){
        return (T) this.configuration.getMap().get(name);
    }
}
