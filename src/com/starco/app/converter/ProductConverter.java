package com.starco.app.converter;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.starco.app.controller.ProductController;
import com.starco.app.controller.RawMaterialController;
import com.starco.app.controller.VendorController;
import com.starco.app.model.Product;
import com.starco.app.model.Vendor;

@FacesConverter(value = "productConverter")
public class ProductConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext ctx, UIComponent uiComponent, String productId) {
        ValueExpression vex =
                ctx.getApplication().getExpressionFactory()
                        .createValueExpression(ctx.getELContext(),
                                "#{productController}", ProductController.class);

        ProductController productController = (ProductController)vex.getValue(ctx.getELContext());
        return productController.getProduct(Integer.valueOf(productId));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object product) {
        return Integer.toString(((Product)product).getId());
    }


}
