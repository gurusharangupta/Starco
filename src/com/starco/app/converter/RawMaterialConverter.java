package com.starco.app.converter;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.starco.app.controller.RawMaterialStarcoController;
import com.starco.app.controller.VendorController;
import com.starco.app.model.RawMaterialsStarco;
import com.starco.app.model.Vendor;


@FacesConverter(value = "rawMaterialConverter")
public class RawMaterialConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext ctx, UIComponent uiComponent, String rawMaterialId) {
        ValueExpression vex =
                ctx.getApplication().getExpressionFactory()
                        .createValueExpression(ctx.getELContext(),
                                "#{rawMaterialStarcoController}", RawMaterialStarcoController.class);

        RawMaterialStarcoController rawMaterialStarcoController = (RawMaterialStarcoController)vex.getValue(ctx.getELContext());
        return rawMaterialStarcoController.getRawMaterialStarco(Integer.valueOf(rawMaterialId));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object rawMaterial) {
        return Integer.toString(((RawMaterialsStarco)rawMaterial).getId());
    }


}