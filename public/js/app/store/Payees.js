Ext.define('PoupaNiquel.store.Payees', {
    extend: 'Ext.data.Store',
    model: 'PoupaNiquel.model.Payee',
    
    storeId: 'Payees',
    autoLoad: true,
    autoSync: true,
    pageSize: 1000,
    
    proxy: {
        type: 'rest',
        api: {
        	read : '/payees/read',
        	create : '/payees/create',
            update: '/payees/update',
            destroy: '/payees/delete'
        },
        
        reader: {
            type: 'json',
            root: 'data',
            successProperty: 'success'
        },
        
        writer: {
            type: 'json',
            root: 'data',
        },
        
        listeners: {
            exception: function(proxy, response, operation){
            	var message = Ext.JSON.decode(response.responseText).message;
            	Ext.Msg.alert('Error', message);
            }
        }
    }
});