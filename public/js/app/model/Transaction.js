Ext.define('PoupaNiquel.model.Transaction', {
    extend: 'Ext.data.Model',
    fields: [
    	'id','description', 'amount', 'balance', 'payment', 'category', 'payee',
    	{ name: 'transactionDate', type: 'date', convert: function(value) { 
    		    var dt = new Date(Date.parse(value));
    	        return Ext.Date.format(dt, "Y-m-d");
    	    }
    	},
    	{name: 'categoryName', mapping:'category.name'},
    	{name: 'payeeName', mapping: function(transaction) {
	            if (transaction.payee) {
	                return transaction.payee.name;
	            }
	            return null;
	        }
    	}
    ],
    
    associations: [
       { type: 'belongsTo', model: 'Generic', primaryKey: 'id', foreignKey: 'category' },
       { type: 'belongsTo', model: 'Generic', primaryKey: 'id', foreignKey: 'payee' }
    ],
  
});