import org.json.JSONObject;

public class FinBuddyFormGenerator {

    public static String generateFilledForm(String userMessage) {
        // Simulated user responses (in a real scenario, parse these from userMessage)
        String name = "Ramesh Kumar";
        String location = "Village A, District B, State C";
        String businessType = "Tailoring";
        String income = "₹15,000 per month";
        String loanCategory = "Kishore"; // Determined based on income and business maturity
        String loanAmountRequested = "₹2,00,000";
        String mobile = "9876543210";
        String aadhaarNumber = "1234-5678-9012";

        // Create the JSON object
        JSONObject applicationDraft = new JSONObject();
        applicationDraft.put("name", name);
        applicationDraft.put("location", location);
        applicationDraft.put("business_type", businessType);
        applicationDraft.put("income", income);
        applicationDraft.put("loan_category", loanCategory);
        applicationDraft.put("loan_amount_requested", loanAmountRequested);
        applicationDraft.put("mobile", mobile);
        applicationDraft.put("aadhaar_number", aadhaarNumber);

        // Return the JSON object as a string
        return applicationDraft.toString(4); // Pretty print with indentation
    }

    public static void main(String[] args) {
        String userMessage = "I earn ₹15,000 per month as a tailor. I need a loan to expand my business.";
        String filledForm = generateFilledForm(userMessage);
        System.out.println(filledForm);
    }
}