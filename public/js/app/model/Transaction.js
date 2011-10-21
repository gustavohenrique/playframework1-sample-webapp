Ext.define('PoupaNiquel.model.Transaction', {
    extend: 'Ext.data.Model',
    fields: [
    	'id','description', 'amount', 'balance', 'payment',  {
    		name: 'payee', convert: function(value, record) { return value.name; }
    	}, {
    		name: 'category', convert: function(value, record) { return value.name; }
    	}, {
    		name: 'categoryId', mapping: 'category.id'
    	}, {
    		name: 'transactionDate', type: 'date', convert: function(value) { 
    		    var dt = new Date(Date.parse(value));
    		    return Ext.Date.format(dt, "Y-m-d");
    	    }
    	}
    ],
    
});