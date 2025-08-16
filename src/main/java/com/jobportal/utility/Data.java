package com.jobportal.utility;

public class Data {
 	public static String getMessage(String otp) {
 		return "<html>" +
                "<body style='font-family: Arial, sans-serif; background-color: #f6f6f6; padding: 20px;'>" +
                "<div style='max-width: 600px; margin: auto; background-color: white; padding: 30px; border-radius: 10px;'>" +
                "<h2 style='color: #2d3436;'>Your Verification Code</h2>" +
                "<p>Hello,</p>" +
                "<p>Use the following OTP to complete your login. It is valid for 10 minutes:</p>" +
                "<div style='background-color: #dff9fb; color: #130f40; font-size: 24px; font-weight: bold; " +
                "padding: 15px; text-align: center; border-radius: 5px; letter-spacing: 4px;'>" +
                otp +
                "</div>" +
                "<p>If you did not request this code, you can safely ignore this email.</p>" +
                "<p style='color: #b2bec3; font-size: 12px;'>© 2025 Your Company. All rights reserved.</p>" +
                "</div>" +
                "</body>" +
                "</html>";

 	}
}
