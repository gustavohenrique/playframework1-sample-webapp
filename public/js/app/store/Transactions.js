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
        },
        
        reader: {
            type: 'json',
            root: 'data',
            successProperty: 'success',
            totalProperty: 'total'
        },
    
        listeners: {
            exception: function(proxy, response, operation){
            	var data = Ext.JSON.decode(response.responseText).data;
            	this.extraParams = {};
            	this.extraParams.accountId = data.accountId;
            	Ext.Msg.alert('Error', data.message);
            }
        }
    }
});