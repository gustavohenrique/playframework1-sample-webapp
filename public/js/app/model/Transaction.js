var balance = 0;

Ext.define('PoupaNiquel.model.Transaction', {
    extend: 'Ext.data.Model',
    fields: [
    	'id','description', 'amount', 'payment', 'account', 'payee', 'category',
	    { name: 'transactionDate', type: 'date', convert: function(value) {
    			if (value != "") {
			        var dt = new Date(Date.parse(value));
		            return Ext.Date.format(dt, "Y-m-d");
    			}
    			return value;
		    }
		},
		{ name: 'balance', mapping: function(transaction) {
			balance = balance + transaction.amount;
			return balance;
		}}
    ],
    
});