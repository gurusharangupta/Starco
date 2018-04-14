package com.starco.app.converter;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.starco.app.controller.ClientController;
import com.starco.app.controller.PackingController;
import com.starco.app.model.Client;
import com.starco.app.model.Packing;

@FacesConverter(value = "clientConverter")
public class ClientConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext ctx, UIComponent uiComponent, String clientId) {
        ValueExpression vex =
                ctx.getApplication().getExpressionFactory()
                        .createValueExpression(ctx.getELContext(),
                                "#{clientController}", ClientController.class);

        ClientController clientController = (ClientController)vex.getValue(ctx.getELContext());
        return clientController.getClient(Integer.valueOf(clientId));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object client) {
        return Integer.toString(((Client)client).getId());
    }


}