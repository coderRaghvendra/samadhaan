package com.samadhaan4u.dto;

/**
 * Created by raghvendra.mishra on 19/02/18.
 */
public enum ServiceType {
    INCOME_TAX("Income Tax", "Pay your income tax through us", "tax-md.png"),
    GST("GST", "Know what is GST..", "gst.png"),
    E_TDS("E-TDS Filing", "We help with e-tds filing", "e-tds-filing.png"),
    TAX_AUDIT("Tax Audit", "Get help in tax auditing", "tax-audit.png"),
    ROC("ROC", "Return on Capital", "roc.png"),
    DIGI_SIGN("Digital Signature", "Digital signature", "digital-sign.png"),
    PAN("PAN Card", "Get your PAN card made", "pan-card.png"),
    TALLY("Book Keeping-Tally", "Get help in book keeping", "book-keeping.png"),
    BUSINESS_LOAN("Business Loan", "Get loan for your business", "business-loan.png"),
    HOME_LOAN("Home Loan", "Get home loan easily", "home-loan.png"),
    PERSONAL_LOAN("Personal Loan", "Get personal loan in seconds", "personal-loan.png"),
    ELEC_BILL("Electricity Bill", "Pay your bills through us", "bill.png");
//    INSURANCE("Insurance Premium Payment", "Pay your insurance premium", "insurance.png");

    private final String heading;
    private final String description;
    private final String imagePath;

    ServiceType(String heading, String description, String imagePath) {
        this.heading = heading;
        this.description = description;
        this.imagePath = imagePath;
    }

    public String getHeading() {
        return heading;
    }

    public String getDescription() {
        return description;
    }

    public String getImagePath() {
        return imagePath;
    }
}
