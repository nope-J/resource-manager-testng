package ctiLink.resourceManager;


/**
 * Created by nope-J on 2016/6/7.
 */
public class CtiLinkRouterResponse extends CtiLinkRouter{
    private CtiLinkRouterset ctiLinkRouterset;

    private CtiLinkGateway ctiLinkGateway;

    public void setCtiLinkRouterset(CtiLinkRouterset ctiLinkRouterset){
        this.ctiLinkRouterset = ctiLinkRouterset;
    }

    public CtiLinkRouterset getCtiLinkRouterset(){
        return ctiLinkRouterset;
    }

    public void setCtiLinkGateway(CtiLinkGateway ctiLinkGateway){
        this.ctiLinkGateway = ctiLinkGateway;
    }

    public CtiLinkGateway getCtiLinkGateway(){
        return ctiLinkGateway;
    }

    public Integer getGatewayId() {
        return super.getGatewayId();
    }

    public Integer getRoutersetId() {
        return super.getRoutersetId();
    }

    public void setRoutersetId(Integer routersetId) {
        super.setRoutersetId(routersetId);
    }

}
