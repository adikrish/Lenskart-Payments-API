package org.lenskart.core;

public class PaymentPathConstants {

	public static final String PAYMENT_METHODS_PATH = "/juno-payment/v1/payment-methods/";
	public static final String MAKE_PAYMENT_PATH = "/juno-order-payment-gateway/v1/orderpayment";
	public static final String VERIFY_PAYMENT_PATH = "/juno-payment/v1/payments/{paymentId}/status";
	public static final String CREATE_PAYMENT_PATH = "/juno-payment/v1/payments";
	public static final String PROCESS_PG_RESPONSE_PATH = "/juno-payment/v1/payments/{paymentId}/paymentgateway-response/";
}
