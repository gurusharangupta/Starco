package com.starco.app.converter;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.starco.app.controller.PackingController;
import com.starco.app.controller.RawMaterialController;
import com.starco.app.controller.VendorController;
import com.starco.app.model.Packing;
import com.starco.app.model.Vendor;

@FacesConverter(value = "packingConverter")
public class PackingConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext ctx, UIComponent uiComponent, String packingId) {
        ValueExpression vex =
                ctx.getApplication().getExpressionFactory()
                        .createValueExpression(ctx.getELContext(),
                                "#{packingController}", PackingController.class);

        PackingController packingController = (PackingController)vex.getValue(ctx.getELContext());
        return packingController.getPacking(Integer.valueOf(packingId));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object packing) {
        return Integer.toString(((Packing)packing).getId());
    }


}
