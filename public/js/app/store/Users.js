Ext.define('PoupaNiquel.store.Users', {
    extend: 'Ext.data.Store',
    model: 'PoupaNiquel.model.User',
    
    storeId: 'Users',
    autoLoad: false,
    autoSync: false,
    pageSize: 35,
    
    proxy: {
        type: 'rest',
        
        api: {
        	create : '/users/create',
        },
        
        reader: {
            type: 'json',
            root: 'data',
            successProperty: 'success'
        },
        
        writer: {
            type: 'json',
            root: 'data',
            successProperty: 'success'
        },
        
        listeners: {
            exception: function(proxy, response, operation) {
                var json = Ext.decode(response.responseText);
                
                if (json) {
                    Ext.MessageBox.show({
                        title: 'Save Failed',
                        msg: json.message,
                        icon: Ext.MessageBox.ERROR,
                        buttons: Ext.Msg.OK
                    });
                } else
                Ext.MessageBox.show({
                    title: 'EXCEPTION',
                    msg: operation.getError(),
                    icon: Ext.MessageBox.ERROR,
                    buttons: Ext.Msg.OK
                });
            },
            
        }
    }
});