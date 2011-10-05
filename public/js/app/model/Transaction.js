Ext.define('PoupaNiquel.model.Transaction', {
    extend: 'Ext.data.Model',
    fields: [
    	'id', 'description', 'transactionDate', 'amount', 'balance', 'payment',
    	{ name: 'payee', convert: function(value, record) { return value.name; } },
    	{ name: 'category', convert: function(value, record) { return value.name; } },
    ],
});