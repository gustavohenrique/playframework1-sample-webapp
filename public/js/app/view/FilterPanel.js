Ext.define('PoupaNiquel.view.FilterPanel' ,{
	extend: 'Ext.panel.Panel',
	
	id: 'filterPanel',
    alias: 'widget.filterPanel',
    title: 'Filter',
    minWidth: 200,
    width: 250,
    split: true,
    border: false,
    frame: false,
    bodyStyle: 'padding-left:5px;padding-top:5px;background:white',
    collapsible: true,
    autoScroll: true,
    xtype: 'form',
    labelWidth: 5,
    defaultType: 'textfield',
    defaults: { anchor: '90%' },
    items: [{
        xtype: 'datefield',
        format: 'd/m/Y',
        name: 'start_date',
        emptyText: 'Start date',
    }, {
        xtype: 'datefield',
        format: 'd/m/Y',
        name: 'end_date',
        emptyText: 'End Date',
        
    }, {
    	xtype: 'combo',
    	fieldLabel: '',
    	name : 'accounts',
    	emptyText: 'Accounts',
    	id: 'accounts',
    	store: 'Accounts',
    	valueField: 'id',
    	displayField: 'name',
    	queryMode: 'local',
    	typeAhead: true,
//    	forceSelection: true, 
    	selectOnFocus: true,
    	allowBlank: true,
    	triggerAction: 'all',
    	listClass: 'x-combo-list-small',
    	minChars: 0
    }, {
    	xtype: 'combo',
    	fieldLabel: '',
    	name : 'operationType',
    	emptyText: 'Type',
    	id: 'type',
    	store: new Ext.data.SimpleStore({ fields: ['id', 'name'],  data : [ [ "C", "Crédito" ],  [ "D", "Débito" ],  [ "T", "Transferência" ] ] }),
    	valueField: 'id',
    	displayField: 'name',
    	queryMode: 'local',
    	typeAhead: true,
    	selectOnFocus: true,
    	allowBlank: true,
    	triggerAction: 'all',
    	listClass: 'x-combo-list-small',
    	minChars: 0
    }, {
    	xtype: 'combo',
    	fieldLabel: '',
    	name : 'payment',
    	emptyText: 'Payment',
    	id: 'payment',
    	store: new Ext.data.SimpleStore({ fields: ['id', 'name'],  data : [ [ 5, "Boleto" ],  [ 6, "Cartão Utilidade" ],  [ 8, "Cheque" ],  [ 2, "Depósito" ],  [ 1, "Dinheiro" ],  [ 7, "Débito em Conta" ],  [ 9, "Mastercard" ],  [ 3, "Transferência" ],  [ 4, "Visa" ] ] }),
    	valueField: 'id',
    	displayField: 'name',
    	queryMode: 'local',
    	typeAhead: true,
    	selectOnFocus: true,
    	allowBlank: true,
    	triggerAction: 'all',
    	listClass: 'x-combo-list-small',
    	minChars: 0
    }, {
    	xtype: 'combo',
    	fieldLabel: '',
    	name : 'payee',
    	emptyText: 'Payee',
    	id: 'payee',
    	store: new Ext.data.SimpleStore({ fields: ['id', 'name'],  data : [ [ 3, "Casas Bahia" ],  [ 4, "Estácio" ],  [ 1, "Feedback" ],  [ 2, "livrosdeinformatica.com.br" ] ] }),
    	valueField: 'id',
    	displayField: 'name',
    	queryMode: 'local',
    	typeAhead: true,
    	selectOnFocus: true,
    	allowBlank: true,
    	triggerAction: 'all',
    	listClass: 'x-combo-list-small',
    	minChars: 0
    }, {
    	xtype: 'combo',
    	fieldLabel: '',
    	name : 'category',
    	emptyText: 'Category',
    	id: 'category',
    	store: new Ext.data.SimpleStore({ fields: ['id', 'name'],  data : [ [ 3, "Alimentação" ],  [ 4, "Combustível" ],  [ 2, "Cursos e Palestras" ],  [ 5, "Energia Elétrica" ],  [ 6, "Higiene e Saúde" ],  [ 7, "Impostos" ],  [ 13, "Internet e Informática" ],  [ 12, "Livros e Apostilas" ],  [ 17, "Moradia" ],  [ 14, "Móveis e Utensílios" ],  [ 15, "Outros" ],  [ 11, "Produtos e Acessórios" ],  [ 1, "Salário" ],  [ 8, "Taxas Bancárias" ],  [ 16, "Telefone" ],  [ 9, "Transporte Público" ],  [ 10, "Viagem" ] ] }),
    	valueField: 'id',
    	displayField: 'name',
    	queryMode: 'local',
    	typeAhead: true,
    	selectOnFocus: true,
    	allowBlank: true,
    	triggerAction: 'all',
    	listClass: 'x-combo-list-small',
    	minChars: 0
    },  Ext.create('Ext.ux.form.MultiSelect', {
        title: 'tags',
        fieldLabel: '',
        name: 'tags',
        height: 180,
        width: 210,
        allowBlank: true,
        mode: 'local',
        hideOnSelect: false,
        store: [  [ "aluguel", "aluguel" ],  [ "amigos", "amigos" ],  [ "animal", "animal" ],  [ "besteira", "besteira" ],  [ "cartao", "cartao" ],  [ "celular", "celular" ],  [ "claro", "claro" ],  [ "curso", "curso" ],  [ "evento", "evento" ],  [ "faculdade", "faculdade" ],  [ "familia", "familia" ],  [ "unibanco", "unibanco" ],  [ "velox", "velox" ],  [ "viagem", "viagem" ],  [ "visa", "visa" ] ],
        tbar: [{
            text: 'Limpar Seleção',
            iconCls: 'eraser-icon',
            cls:'x-btn-text-icon',
            handler: function() {
                Ext.getCmp('frmFiltro').getForm().findField('tags').reset();
            }
        }]
    })],
    buttons: [{
        text: 'Limpar',
        handler: function() {
            Ext.getCmp('frmFiltro').getForm().reset();
        }
    }, {
        text: 'Filtrar',
        handler: function() {
            var form = Ext.getCmp('frmFiltro').getForm();
            var store = Ext.getCmp('gridLancamento').getStore();
            if (form.isValid())
                store.reload({ params: form.getValues() });
            else
                Ext.Msg.alert('Erro','Preencha corretamente o formulário');
        }
    }],
     
    initComponent: function() {
        return this.callParent(arguments);
    }
});