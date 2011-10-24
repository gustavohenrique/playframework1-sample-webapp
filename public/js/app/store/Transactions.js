Ext.define('PoupaNiquel.store.Transactions', {
    extend: 'Ext.data.Store',
    model: 'PoupaNiquel.model.Transaction',
    
    autoLoad: false,
    pageSize: 10,
    remoteFilter: false,
    remoteSort: false,
    
    proxy: {
        type: 'rest',
    	
        api: {
        	read : '/transactions/filter',
        	create : '/transactions/create',
            update: '/transactions/update',
            destroy: '/transactions/delete'
        },
        
        reader: {
            type: 'json',
            root: 'data',
            successProperty: 'success',
            totalProperty: 'total'
        },
        
        writer: {
            type: 'json',
            root: 'data',
            writeAllFields: true,
            encode: false,
        },
        
        listeners: {
            exception: function(proxy, response, operation){
//            	var data = Ext.JSON.decode(response.responseText).data;
            	var accountId = this.extraParams.accountId;
            	this.extraParams = {};
            	this.extraParams.accountId = accountId;
            	Ext.Msg.alert('Error', 'No transactions found.');
            }
        }
    }
});