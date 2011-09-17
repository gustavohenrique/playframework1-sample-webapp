Ext.Loader.setConfig({enabled: true});
Ext.Loader.setPath('Ext.ux', '/public/js/extjs/ux');
Ext.require(['Ext.ux.form.MultiSelect']);

Ext.define('PoupaNiquel.view.FilterPanel' ,{
	extend: 'Ext.panel.Panel',
	alias: 'widget.filterpanel',

    minWidth: 200,
    width: 250,
    split: true,
    border: false,
    frame: false,
//    plain: true,
//    bodyStyle: 'padding:10px;background:white',
    bodyPadding: 10,
    collapsible: true,
    autoScroll: true,
    layout: 'anchor',
    labelWidth: 5,
    defaultType: 'textfield',
    defaults: { anchor: '100%' },
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
    },  {
    	xtype: 'multiselect',
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
    }],
    
     
    initComponent: function() {
        return this.callParent(arguments);
    }
});