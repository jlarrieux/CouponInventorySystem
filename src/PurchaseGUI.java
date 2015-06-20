import javax.swing.*;
import java.awt.*;

/**
 * Created by Jeannius on 6/20/2015.
 */
public class PurchaseGUI extends JFrame {

    JTextField couponProviderNameTextField, productNameTextField, priceTextField, discountRateTextField, expirationDateTextfield;
    JLabel couponProviderNameLabel, ProductNameLabel, priceLabel, discountRateLabel, expirationDateLabel;

    public PurchaseGUI(){
        setLayout(new GridLayout(2,1));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();

    }

    private void init(){
        labelConstructor();
        textFieldConstructor();
        JPanel field = new JPanel(new GridLayout(2,5));

        field.add(couponProviderNameLabel);
        field.add(ProductNameLabel);
        field.add(priceLabel);
        field.add(discountRateLabel);
        field.add(expirationDateLabel);
        field.add(couponProviderNameTextField);
        field.add(productNameTextField);
        field.add(discountRateTextField);
        field.add(expirationDateTextfield);
        field.add(priceTextField);

        add(field);

    }

    private void labelConstructor(){
        couponProviderNameLabel = new JLabel("Coupon Provider");
        couponProviderNameLabel.setHorizontalAlignment(JLabel.CENTER);
        ProductNameLabel = new JLabel("Product Name");
        ProductNameLabel.setHorizontalAlignment(JLabel.CENTER);
        discountRateLabel = new JLabel("Discount Rate");
        discountRateLabel.setHorizontalAlignment(JLabel.CENTER);
        expirationDateLabel= new JLabel("Expiration Date");
        expirationDateLabel.setHorizontalAlignment(JLabel.CENTER);
        priceLabel = new JLabel("Price");
        priceLabel.setHorizontalAlignment(JLabel.CENTER);
    }

    private void textFieldConstructor(){
        couponProviderNameTextField = new JTextField();
        productNameTextField = new JTextField();
        priceTextField = new JTextField();
        discountRateTextField = new JTextField();
        expirationDateTextfield =new JTextField();

    }


}
