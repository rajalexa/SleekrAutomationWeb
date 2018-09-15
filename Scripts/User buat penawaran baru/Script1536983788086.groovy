import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData

import sleekrPackage.TestSteps

TestSteps act = new TestSteps()

act.customLogin(loginActiveUser, loginActivePassword)

act.customClick(findTestObject('OrganisasiPage/english_language_button'))

act.customClick(findTestObject('OrganisasiPage/organisasi_first_list'))

act.customClick(findTestObject('PTTestQaPage/pt_test_qa_add_new_button'))

act.customClick(findTestObject('PTTestQaPage/pt_test_qa_add_offer'))

WebUI.click(findTestObject('PTTestQaPage/pt_test_qa_add_new_line_button'))

WebUI.click(findTestObject('PTTestQaPage/pt_test_qa_first_item'))

act.selectDropdownWithValue(findTestObject('PTTestQaPage/pt_test_qa_item_dropdown'), findTestData('TestData').getValue('Value', 2))

WebUI.click(findTestObject('PTTestQaPage/pt_test_qa_first_description'))

act.customSetText(findTestObject('PTTestQaPage/pt_test_qa_input_description'), findTestData('TestData').getValue('Value', 6))

WebUI.click(findTestObject('PTTestQaPage/pt_test_qa_second_item'))

act.selectDropdownWithValue(findTestObject('PTTestQaPage/pt_test_qa_item_dropdown'), findTestData('TestData').getValue('Value', 3))

WebUI.click(findTestObject('PTTestQaPage/pt_test_qa_second_description'))

act.customSetText(findTestObject('PTTestQaPage/pt_test_qa_input_description'), findTestData('TestData').getValue('Value', 6))

WebUI.click(findTestObject('PTTestQaPage/pt_test_qa_third_item'))

act.selectDropdownWithValue(findTestObject('PTTestQaPage/pt_test_qa_item_dropdown'), findTestData('TestData').getValue('Value', 4))

WebUI.click(findTestObject('PTTestQaPage/pt_test_qa_third_description'))

WebUI.click(findTestObject('PTTestQaPage/pt_test_qa_third_description'))

act.customSetText(findTestObject('PTTestQaPage/pt_test_qa_input_description'), findTestData('TestData').getValue('Value', 6))

WebUI.click(findTestObject('PTTestQaPage/pt_test_qa_fourth_item'))

act.selectDropdownWithValue(findTestObject('PTTestQaPage/pt_test_qa_item_dropdown'), findTestData('TestData').getValue('Value', 5))

WebUI.click(findTestObject('PTTestQaPage/pt_test_qa_fourth_description'))

act.customSetText(findTestObject('PTTestQaPage/pt_test_qa_input_description'), findTestData('TestData').getValue('Value', 6))

WebUI.scrollToElement(findTestObject('PTTestQaPage/pt_test_qa_customer_dropdown'), 0)

act.customClick(findTestObject('PTTestQaPage/pt_test_qa_customer_dropdown'))

act.customClick(findTestObject('PTTestQaPage/pt_test_qa_customer_dropdown_add_new_contact'))

String customName = "AlexJack " + act.setRandom("String", 0, 9999)

act.customSetText(findTestObject('PTTestQaPage/pt_test_qa_new_contact_input_full_name'), customName)

WebUI.click(findTestObject('PTTestQaPage/pt_test_qa_new_contact_submit_button'))

act.selectDateWithValue(findTestObject('PTTestQaPage/pt_test_qa_offering_date'), "29/12/2018")

WebUI.setText(findTestObject('PTTestQaPage/pt_test_qa_due_date'), "29-12-2019")

WebUI.click(findTestObject('PTTestQaPage/pt_test_qa_show_detail_button'))

WebUI.setText(findTestObject('PTTestQaPage/pt_test_qa_input_reference'), "Test Alex")

act.selectDropdownWithValue(findTestObject('PTTestQaPage/pt_test_qa_salesman_dropdown'), findTestData('TestData').getValue('Value', 1))

WebUI.click(findTestObject('PTTestQaPage/pt_test_qa_add_withholding_tax'))

WebUI.click(findTestObject('PTTestQaPage/pt_test_qa_first_tax'))

act.selectDropdownWithValue(findTestObject('PTTestQaPage/pt_test_qa_tax_dropdown'), findTestData('TestData').getValue('Value', 7))

WebUI.scrollToElement(findTestObject('PTTestQaPage/pt_test_qa_save_button'), 0)

WebUI.click(findTestObject('PTTestQaPage/pt_test_qa_save_button'))

WebUI.click(findTestObject('PTTestQaPage/pt_test_qa_save_as_draft_button'))




