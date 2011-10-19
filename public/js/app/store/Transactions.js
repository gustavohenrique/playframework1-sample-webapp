Ext.define('PoupaNiquel.store.Transactions', {
    extend: 'Ext.data.Store',
    model: 'PoupaNiquel.model.Transaction',
    
    autoLoad: false,
    pageSize: 10,
    remoteFilter: false,
    remoteSort: false,
    
    proxy: {
        type: 'ajax',
    	
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
    
//        listeners: {
//            exception: function(proxy, response, operation){
//            	var data = Ext.JSON.decode(response.responseText).data;
//            	this.extraParams = {};
//            	this.extraParams.accountId = data.accountId;
//            	Ext.Msg.alert('Error', data.message);
//            }
//        }
    }
});