require 'rest-client'
require 'rspec'
require 'singleton-client'

describe "get_translations online test" do
    before :each do
        SgtnClient::Config.instance_variable_set(:@loader, nil)
        Sgtn.load_config("./config/sgtnclient.yml", "onlysourcetranslations")
    end

    context "get_translations methods" do


        it "get component by get_translations and arguments is more than 2" do
            expect{Sgtn.get_translations("about", 'en', "add")}.to raise_error(ArgumentError)
            
        end


        it "get component by get_translations and arguments is empty" do
            Sgtn.locale = "de"
            expect{Sgtn.get_translations()}.to raise_error(ArgumentError)
        end

        it "get component by get_translations" do
            expect(Sgtn.get_translations("about", 'en')).to eq({"component"=>"about", "locale"=>"en", "messages"=>{"about.key1"=>"test value 1", "about.key2"=>"test value 2", "about.key3"=>"test value 3", "about.key4"=>"test value 4"}})
        end

        it "get component by get_translations and component is int" do
            expect(Sgtn.get_translations(123, 'en')).to eq(nil)
        end

        it "get component by get_translations and component is nil" do
            expect(Sgtn.get_translations(nil, 'en')).to eq(nil)
        end

        
        it "get component by get_translations and component is not exist" do
            expect(Sgtn.get_translations("notexist", 'en')).to eq(nil)
        end
        
        it "get component by get_translations and component not in source" do
            expect(Sgtn.get_translations("contact", 'en')).to eq(nil)
        end

        it "get component by get_translations and component not in source and locale is de" do
            expect(Sgtn.get_translations("contact", 'de')).to eq(nil)
        end

        it "get component by get_translations" do
            expect(Sgtn.get_translations("about2", 'de')).to eq({"component"=>"about2", "locale"=>"en", "messages"=>{"about2.key1"=>"test value 1", "about2.key2"=>"test value 2"}})
        end

        
        it "get component by get_translations and locale is en-UK" do
            expect(Sgtn.get_translations("intest", "en_UK")).to eq({"component"=>"intest", "locale"=>"en", "messages"=>{"intest.key1"=>"add value 1", "intest.key2"=>"add value source 2", "intest.key4"=>"add value 4"}})
        end




    end



end