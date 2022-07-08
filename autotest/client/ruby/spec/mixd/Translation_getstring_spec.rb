require 'rest-client'
require 'rspec'
require 'singleton-client'

describe "Translation test" do
    before :each do
        SgtnClient::Config.instance_variable_set(:@loader, nil)
        Sgtn.load_config("./config/sgtnclient.yml", "mixed")
        #SgtnClient::Source.loadBundles("default")
    end

    context "Get string's translation" do

        it "Get a string's translation and input parameters more than interface parameters" do
            expect{SgtnClient::Translation.getString("allin", "about.message", "zh-CN","de")}.to raise_error(ArgumentError)
        end

        it "Get a string's translation and input parameters less than interface parameters" do
            expect{SgtnClient::Translation.getString("allin", "about.message")}.to raise_error(ArgumentError)
        end

        it "Get a string's translation and input parameters less than interface parameters" do
            expect{SgtnClient::Translation.getString()}.to raise_error(ArgumentError)
        end

        it "Get a string's translation and locale is en-US" do
            expect(SgtnClient::Translation.getString("allin", "about.message", "en-US")).to eq("Your application description page. offline")
        end

        it "Get a string's translation and component type is incorrect" do
            #expect{SgtnClient::Translation.getString(123, "about.message", "de")}.to raise_error(TypeError)
            expect(SgtnClient::Translation.getString(123, "about.message", "de")).to eq(nil)
        end

        it "Get a string's translation and component not exist in server and not exist in source" do
            expect(SgtnClient::Translation.getString("aboutnotexist", "about.key", "de")).to eq(nil)
        end

        it "Get a string's translation and component exist in server but not exist in source" do
            expect(SgtnClient::Translation.getString("contact", "contact.title", "en")).to eq(nil)
        end

        it "Get a string's translation and component not exist in server but exist in source" do
            expect(SgtnClient::Translation.getString("onlyonline", "contact.message", "de")).to eq("Your contact de page.")
        end
        
        
        it "Get a string's translation and key type is incorrect" do
            expect(SgtnClient::Translation.getString("allin", 123, "de")).to eq(nil)
        end

        it "Get a string's translation and key not exist in server and not exist in source--bug" do
            expect(SgtnClient::Translation.getString("allin", "about.key134", "de")).to eq(nil)
        end

        it "Get a string's translation and key not exist in server but exist in source" do
            expect(SgtnClient::Translation.getString("allin", "about.test1", "en")).to eq("test {1}")
        end

        it "Get a string's translation and key exist in server but not exist in source" do
            expect(SgtnClient::Translation.getString("allin", "about.title", "en")).to eq(nil)
        end

        it "Get a string's translation and en is not enqual source" do
            expect(SgtnClient::Translation.getString("allin", "about.description", "en")).to eq("Use this area to provide additional information source")
        end

        it "Get a string's translation and locale is de" do
            expect(SgtnClient::Translation.getString("allin", "about.message", "de")).to eq("test de key offline")
        end

        it "Get a string's translation and locale is da(not exist in bundle)" do
            expect(SgtnClient::Translation.getString("allin", "about.message", "da")).to eq("Your application description page. offline")
        end

        it "Get a string's translation and unsupport locale(xxxxx)" do
            expect(SgtnClient::Translation.getString("allin", "about.message", "xxxxx")).to eq("Your application description page. offline")
        end

        it "Get a string's translation and locale is nil" do
            Sgtn.locale = nil
            expect(SgtnClient::Translation.getString("allin", "about.message", nil)).to eq("Your application description page. offline")
        end

        it "Get a string's translation and locale is int" do
            expect(SgtnClient::Translation.getString("allin", "about.message", 123)).to eq("Your application description page. offline")
        end
        
        it "Get a string's translation and locale is en_UK" do
            expect(SgtnClient::Translation.getString("allin", "about.message", "en_UK")).to eq("Your application description page. offline")
        end

        it "Get a string's translation and locale is zh-Hans-CN" do
            expect(SgtnClient::Translation.getString("allin", "about.message", "zh-Hans-CN")).to eq("应用程序说明页。")
        end


    end



end