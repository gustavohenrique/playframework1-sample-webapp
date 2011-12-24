Ext.define('PoupaNiquel.model.User', {
    extend: 'Ext.data.Model',
    fields: ['id', 'username', 'password', 'fullname'],
    
    proxy: {
        type: 'rest',
        
        api: {
        	create : '/users/create',
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