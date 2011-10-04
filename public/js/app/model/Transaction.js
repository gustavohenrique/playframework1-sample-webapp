Ext.define('PoupaNiquel.model.Transaction', {
    extend: 'Ext.data.Model',
    fields: ['id', 'description', 'transactionDate', 'amount', 'balance', 'account', 'payee', 'category', 'payment'],
    	
	associations: [{
        type: 'hasMany',
        model: 'PoupaNiquel.model.Account',
        primaryKey: 'id',
        foreignKey: 'account_id',
        associationKey: 'account',
        autoLoad: false,
    }]
});