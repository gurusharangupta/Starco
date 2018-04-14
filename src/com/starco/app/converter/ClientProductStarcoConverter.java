package com.starco.app.converter;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.starco.app.controller.ClientController;
import com.starco.app.controller.PackingController;
import com.starco.app.controller.SalesController;
import com.starco.app.model.Client;
import com.starco.app.model.ClientProductStarco;
import com.starco.app.model.Packing;

@FacesConverter(value = "clientProductStarcoConverter")
public class ClientProductStarcoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext ctx, UIComponent uiComponent, String clientProductStarcoId) {
        ValueExpression vex =
                ctx.getApplication().getExpressionFactory()
                        .createValueExpression(ctx.getELContext(),
                                "#{salesController}", SalesController.class);

        SalesController salesController = (SalesController)vex.getValue(ctx.getELContext());
        return salesController.getClientProductStarco(Integer.valueOf(clientProductStarcoId));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object clientProductStarco) {
        return Integer.toString(((ClientProductStarco)clientProductStarco).getId());
    }


}