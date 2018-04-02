package com.starco.app.converter;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.starco.app.controller.RawMaterialController;
import com.starco.app.controller.VendorController;
import com.starco.app.model.Vendor;

@FacesConverter(value = "vendorConverter")
public class VendorConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext ctx, UIComponent uiComponent, String vendorId) {
        ValueExpression vex =
                ctx.getApplication().getExpressionFactory()
                        .createValueExpression(ctx.getELContext(),
                                "#{vendorController}", VendorController.class);

        VendorController vendorController = (VendorController)vex.getValue(ctx.getELContext());
        return vendorController.getVendor(Integer.valueOf(vendorId));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object vendor) {
        return Integer.toString(((Vendor)vendor).getId());
    }


}
