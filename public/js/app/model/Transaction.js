function getName(object) {
	if (object) {
        return object.name;
    }
    return null;
};

function getId(object) {
	if (object) {
        return object.id;
    }
    return null;
};

Ext.define('PoupaNiquel.model.Transaction', {
    extend: 'Ext.data.Model',
    fields: [
    	'id','description', 'amount', 'balance', 'payment', 'category', 'payee', {
    		name: 'categoryId', mapping: function(transaction) { return getId(transaction.category); }
	    }, {
	    	name: 'categoryName', mapping: function(transaction) { return getName(transaction.category); }
    	}, {
    		name: 'payeeId', mapping: function(transaction) { return getId(transaction.payee); }
	    }, {
	    	name: 'payeeName', mapping: function(transaction) { return getName(transaction.payee); }
    	}, {
    		name: 'transactionDate', type: 'date', convert: function(value) { 
			    var dt = new Date(Date.parse(value));
		        return Ext.Date.format(dt, "Y-m-d");
		    }
		},
    ],
    
    associations: [
       { type: 'belongsTo', model: 'Generic', primaryKey: 'id', foreignKey: 'category' },
       { type: 'belongsTo', model: 'Generic', primaryKey: 'id', foreignKey: 'payee' }
    ],
  
});